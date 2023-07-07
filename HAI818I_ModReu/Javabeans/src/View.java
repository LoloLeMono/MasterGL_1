public abstract class View
{
    Controller cont;
    Model model;
    public View(Model m, Controller c)
    {
        model = m;
        cont = c;
        MV_Association.add(m, this);
    }
    public abstract void update(Object how);
    public void open(){}
    public void redisplay(){}
}