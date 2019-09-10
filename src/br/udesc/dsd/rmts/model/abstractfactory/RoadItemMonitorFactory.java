package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;
import br.udesc.dsd.rmts.model.RoadMonitor;

/**
 * Abstract road item monitor factory
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public class RoadItemMonitorFactory extends AbstractRoadItemFactory {

    @Override
    public RoadItem createRoad(int x, int y) {
        return new RoadMonitor(x, y);
    }

}
