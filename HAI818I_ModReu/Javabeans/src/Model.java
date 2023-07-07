import java.util.Iterator;

public abstract class Model
{
    public void changed(Object how)
    {
        for (View view : MV_Association.getViews(this))
        {
            view.update(how);
        }
    }
}