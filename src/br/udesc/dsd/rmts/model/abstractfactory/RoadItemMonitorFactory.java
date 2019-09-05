package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.CrossRoadMonitor;
import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadMonitor;

public class RoadItemMonitorFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoadItem(int x, int y) {
        return new RoadMonitor(x, y);
    }

    @Override
    public RoadItem createCrossRoad(int x, int y) {
        return new CrossRoadMonitor(x, y);
    }
}
