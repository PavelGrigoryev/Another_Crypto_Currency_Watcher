package com.pavel.anothercryptocurrencywatcher.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pavel.anothercryptocurrencywatcher.dto.CoinDto;
import com.pavel.anothercryptocurrencywatcher.dto.PriceDto;
import com.pavel.anothercryptocurrencywatcher.entity.*;
import com.pavel.anothercryptocurrencywatcher.exception.CoinNotFoundException;
import com.pavel.anothercryptocurrencywatcher.repository.BTCRepository;
import com.pavel.anothercryptocurrencywatcher.repository.CoinRepository;
import com.pavel.anothercryptocurrencywatcher.repository.ETHRepository;
import com.pavel.anothercryptocurrencywatcher.repository.SOLRepository;
import com.pavel.anothercryptocurrencywatcher.service.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Slf4j
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    private final BTCRepository btcRepository;

    private final ETHRepository ethRepository;

    private final SOLRepository solRepository;


    public CoinServiceImpl(CoinRepository coinRepository, BTCRepository btcRepository,
                           ETHRepository ethRepository, SOLRepository solRepository) {
        this.coinRepository = coinRepository;
        this.btcRepository = btcRepository;
        this.ethRepository = ethRepository;
        this.solRepository = solRepository;
    }

    private final List<UserPrice> prices = new ArrayList<>();

    @Override
    public List<CoinDto> getAllCoins() {
        List<Coin> coins = coinRepository.findAll();
        List<CoinDto> dtoList = new ArrayList<>();
        coins.forEach(coin -> dtoList.add(coin.toDto()));
        return dtoList;
    }

    @Override
    public PriceDto getLastPrice(String symbol) {
        return switch (symbol) {
            case "ETH" -> ethRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() -> new CoinNotFoundException("Item " + symbol))
                    .toDto();
            case "BTC" -> btcRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() -> new CoinNotFoundException("Item " + symbol))
                    .toDto();
            case "SOL" -> solRepository.findFirstBySymbolOrderByDateOfReceivingDesc(symbol)
                    .orElseThrow(() -> new CoinNotFoundException("Item " + symbol))
                    .toDto();
            default -> throw new CoinNotFoundException("Item " + symbol);
        };
    }

    @Scheduled(fixedRate = 60000)
    private void getPriceFromCoinLore() {
        WebClient webClient = WebClient.create("https://api.coinlore.net/api/ticker");
        ObjectMapper mapper = new ObjectMapper();

        List<PriceDto> btc = Stream.of(Objects.requireNonNull(webClient.get().uri("/?id=90")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block()))
                .map(object -> mapper.convertValue(object, PriceDto.class)).toList();

        List<PriceDto> eth = Stream.of(Objects.requireNonNull(webClient.get().uri("/?id=80")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block()))
                .map(object -> mapper.convertValue(object, PriceDto.class)).toList();

        List<PriceDto> sol = Stream.of(Objects.requireNonNull(webClient.get().uri("/?id=48543")
                        .accept(MediaType.APPLICATION_JSON).retrieve()
                        .bodyToMono(Object[].class).block()))
                .map(object -> mapper.convertValue(object, PriceDto.class)).toList();

        BTCPrice btcPrice = BTCPrice.fromDto(btc.get(0));
        ETHPrice ethPrice = ETHPrice.fromDto(eth.get(0));
        SOLPrice solPrice = SOLPrice.fromDto(sol.get(0));

        btcRepository.save(btcPrice);
        ethRepository.save(ethPrice);
        solRepository.save(solPrice);
    }

    @Override
    public void addPriceToTracking(UserPrice userPrice) {
        prices.add(userPrice);
    }

    @Scheduled(fixedRate = 60000)
    private void trackPrice() {
        if (!prices.isEmpty()) {
            prices.forEach(userPrice -> {
                double dbPrice = getLastPrice(userPrice.getSymbol()).getPrice();
                log.info("Price for user " + userPrice.getUserName() + " with coin " + userPrice.getSymbol()
                        + " is " + dbPrice);
                double percent = (dbPrice - userPrice.getUserPrice()) / dbPrice * 100;
                if (1 < Math.abs(percent)) {
                    try {
                        Sequencer sequencer = MidiSystem.getSequencer();
                        sequencer.open();

                        Sequence sequence = new Sequence(Sequence.PPQ, 4);
                        Track track = sequence.createTrack();

                        ShortMessage firstMessage = new ShortMessage();
                        firstMessage.setMessage(144, 1, 44, 100);
                        MidiEvent noteOn = new MidiEvent(firstMessage, 1);
                        track.add(noteOn);

                        ShortMessage secondMessage = new ShortMessage();
                        secondMessage.setMessage(128, 1, 44, 100);
                        MidiEvent noteOff = new MidiEvent(secondMessage, 16);
                        track.add(noteOff);

                        sequencer.setSequence(sequence);
                        sequencer.start();

                    } catch (Exception exception) {
                        log.error(exception.getMessage(), exception);
                    }

                    log.warn("Price for user " + userPrice.getUserName() + " with coin " + userPrice.getSymbol()
                            + " changed " + percent);
                }
            });
        }
    }
}
