import com.baixing.bi.utils.ThriftClient;
import com.baixing.thrift.moutan.Moutan;
import com.baixing.thrift.moutan.TSite;
import org.apache.thrift.TException;
import org.junit.Test;

/**
 * Created by zjl on 2017/6/14.
 */
public class ThriftClientTest {

    @Test
    public void testCliet() throws TException {
        ThriftClient cc = new ThriftClient("http://thrift.baixing.com/Moutan", "moutan");
        Moutan.Client client = (Moutan.Client)cc.initClient();
        TSite tsite = client.getSite("mts:6");
        System.out.println(tsite.getCity());
        System.out.println(tsite.getArea());
        System.out.println(tsite.getName());
        System.out.println(tsite.getTitle());
        cc.close();
    }

    @Test
    public void test1() {
        String val = "123";
        System.out.println(val.getClass());
        Boolean b;
        if (val.getClass() == String.class) b = true;
        else b = false;
        System.out.println(b);
    }

}
