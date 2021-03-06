package graviton.network;

import com.google.inject.Inject;
import graviton.api.Manager;
import graviton.api.NetworkService;
import graviton.network.exchange.ExchangeNetwork;
import graviton.network.game.GameNetwork;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Botan on 16/06/2015.
 */

public class NetworkManager implements Manager {

    private final List<NetworkService> services;

    @Inject
    public NetworkManager(ExchangeNetwork exchangeNetwork, GameNetwork gameNetwork, graviton.core.Manager manager) {
        manager.add(this);
        this.services = Arrays.asList(exchangeNetwork, gameNetwork);
    }

    @Override
    public void load() {
        services.forEach(NetworkService::start);
    }

    @Override
    public void unload() {
        services.forEach(NetworkService::stop);
    }

}
