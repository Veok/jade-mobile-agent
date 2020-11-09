
import jade.core.Agent;
import jade.core.Location;
import jade.core.behaviours.TickerBehaviour;

public class GetTickerBehaviour extends TickerBehaviour {

    GetTickerBehaviour(Agent agent, long period) {
        super(agent, period);
    }

    public void onTick() {
        var locations = ((MobileAgent) myAgent).getLocations();
        if (!locations.isEmpty()) {
            var location = (Location) locations.remove(locations.size() - 1);

            System.out.println(
                    "Agent " + myAgent.getLocalName() + " trying to move to new location " + location.toString());

            myAgent.doMove(location);
            WelcomeWindow.createWindow("Hello in " + location + "!", location.toString());
        } else {
            System.out.println("Agent " + myAgent.getLocalName() + " visited all possible places");
            myAgent.doDelete();
        }
    }

  
}