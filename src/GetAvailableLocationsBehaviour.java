import java.util.Vector;

import jade.content.onto.basic.Action;
import jade.content.onto.basic.Result;
import jade.core.Location;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.QueryPlatformLocationsAction;
import jade.domain.mobility.MobilityOntology;
import jade.lang.acl.ACLMessage;
import jade.proto.SimpleAchieveREInitiator;

public class GetAvailableLocationsBehaviour extends SimpleAchieveREInitiator {

    private ACLMessage request;

    public GetAvailableLocationsBehaviour(MobileAgent mobileAgent) {
        super(mobileAgent, new ACLMessage(ACLMessage.REQUEST));

        request = (ACLMessage) getDataStore().get(REQUEST_KEY);
        request.clearAllReceiver();
        request.addReceiver(mobileAgent.getAMS());
        request.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        request.setOntology(MobilityOntology.NAME);
        request.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);

        try {
            var action = new Action();
            action.setActor(mobileAgent.getAMS());
            action.setAction(new QueryPlatformLocationsAction());

            mobileAgent.getContentManager().fillContent(request, action);
        } catch (Exception fe) {
            System.out.println("Error during initializing AvailableLocationsBehaviour");
            fe.printStackTrace();
        }
        reset(request);
    }

    protected void handleNotUnderstood(ACLMessage reply) {
        System.out.println(myAgent.getLocalName() + " handleNotUnderstood : " + reply.toString());
    }

    protected void handleRefuse(ACLMessage reply) {
        System.out.println(myAgent.getLocalName() + " handleRefuse : " + reply.toString());
    }

    protected void handleFailure(ACLMessage reply) {
        System.out.println(myAgent.getLocalName() + " handleFailure : " + reply.toString());
    }

    protected void handleAgree(ACLMessage reply) {
        System.out.println(myAgent.getLocalName() + " handleAgree : " + reply.toString());

    }

    protected void handleInform(ACLMessage inform) {
        try {
            var results = (Result) myAgent.getContentManager().extractContent(inform);
            var locations = new Vector();
            var iter = results.getItems().iterator();

            for (; iter.hasNext();) {
                var obj = iter.next();
                System.out.println("Location: " + obj.toString());
                locations.add((Location) obj);
            }

            ((MobileAgent) myAgent).setLocations(locations);
        } catch (Exception e) {
            System.out.println("Error during handling of inform");
            e.printStackTrace();
        }
    }
}