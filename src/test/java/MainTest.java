import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest
{


    @Test
    public void read()throws Exception
    {
        assert (!(Main.Read("dictionary.txt").size() == 0));
        assert (!(Main.Read("smalldict1.txt").size() == 0));
        assert (!(Main.Read("smalldict2.txt").size() == 0));
        assert (Main.Read("dict1").size() == 0);
        assert (Main.Read("shit").size() == 0);
    }

    @Test
    public void prints()
    {
    }

    @Test
    public void find()
    {

    }

    @Test
    public void main()
    {
    }
}