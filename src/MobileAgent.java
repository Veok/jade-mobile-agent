
import java.util.Vector;

import jade.content.lang.sl.SLCodec;
import jade.core.Agent;
import jade.domain.FIPANames;
import jade.domain.mobility.MobilityOntology;

public class MobileAgent extends Agent {

    private Vector locations;

    protected void setup() {
        System.out.println("Hi! My name is  " + getLocalName());

        System.out.println("Creating locations");
        locations = new Vector();

        System.out.println("Registering language and ontology");
        getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
        getContentManager().registerOntology(MobilityOntology.getInstance());

        System.out.println("Going to behaviours");
        addBehaviour(new GetAvailableLocationsBehaviour(this));
        addBehaviour(new GetTickerBehaviour(this, 6000));
    }

    protected void beforeMove() {
        System.out.println(getLocalName() + " is now moving elsewhere");
    }

    protected void afterMove() {
        var message = getLocalName() + " is just arrived to this locations.";
        System.out.println(message);
    }

    public Vector getLocations() {
        return locations;
    }

    public void setLocations(Vector locations) {
        this.locations = locations;
    }
}
