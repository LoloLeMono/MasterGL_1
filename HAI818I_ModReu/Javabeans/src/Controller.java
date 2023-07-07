import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener
{
    Model model;
    public Controller(Model m)
    {
        this.model = m;
    }
}