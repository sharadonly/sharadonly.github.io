import javax.swing.JFrame;

/**
   Shows a frame with a traffic light
*/
public class TrafficLightViewer
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      frame.setSize(300, 400);
      frame.setTitle("TrafficLightViewer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      TrafficLightComponent component = new TrafficLightComponent();
      frame.add(component);
      frame.setVisible(true);
   }
}