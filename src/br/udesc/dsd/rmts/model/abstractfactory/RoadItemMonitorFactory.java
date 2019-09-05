package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadItemMonitor;

public class RoadItemMonitorFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoadItem(int x, int y) {
        return new RoadItemMonitor(x, y);
    }

}
