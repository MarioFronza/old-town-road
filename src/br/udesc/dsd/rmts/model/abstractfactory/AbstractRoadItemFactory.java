package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;

/**
 * Abstract road item factory
 *
 * @author João Pedro Schmitz, Mário Fronza
 * @version 1.0.0
 */
public abstract class AbstractRoadItemFactory {

    public abstract RoadItem createRoad(int x, int y);

}
