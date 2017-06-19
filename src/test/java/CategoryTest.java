import com.baixing.bi.event.Category;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zjl on 2017/5/31.
 */
public class CategoryTest {

    public static Category category = null;
    static  {
        category = new Category();
        category.loadConfigFile("/Users/zjl/Downloads/category_format.csv");

    }

    @Test
    public void testCategoryNameCn() {
        Assert.assertEquals(category.getFiled("cheliang","categoryCn"), "车辆");
    }

    @Test
    public void testCategoryNameCnNotExist() {
        Assert.assertEquals(category.getFiled("cheliang1","categoryCn"), "NULL");
    }



}
