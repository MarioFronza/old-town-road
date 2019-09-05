package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadItemSemaphore;

public class RoadItemSemaphoreFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoadItem(int x, int y) {
        return new RoadItemSemaphore(x, y);
    }

}
