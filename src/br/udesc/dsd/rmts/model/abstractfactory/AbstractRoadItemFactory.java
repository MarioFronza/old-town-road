package br.udesc.dsd.rmts.model.abstractfactory;

import br.udesc.dsd.rmts.model.RoadItem;

public abstract class AbstractRoadItemFactory {

    public abstract RoadItem createRoadItem(int x, int y);

    public abstract RoadItem createCrossRoad(int x, int y);

}
