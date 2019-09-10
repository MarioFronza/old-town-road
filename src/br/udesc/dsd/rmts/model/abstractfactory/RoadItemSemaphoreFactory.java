package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadSemaphore;

/**
 * Abstract road item semaphore factory
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadItemSemaphoreFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoad(int x, int y) {
        return new RoadSemaphore(x, y);
    }

}
