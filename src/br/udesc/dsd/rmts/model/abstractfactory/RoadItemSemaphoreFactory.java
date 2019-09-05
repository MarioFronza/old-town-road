package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.CrossRoadSemaphore;
import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadSemaphore;

public class RoadItemSemaphoreFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoadItem(int x, int y) {
        return new RoadSemaphore(x, y);
    }

    @Override
    public RoadItem createCrossRoad(int x, int y) {
        return new CrossRoadSemaphore(x, y);
    }
}
