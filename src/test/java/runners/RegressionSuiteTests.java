package runners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.healthCheck.GetHealthCheckTest;
import tests.login.PostAuthLoginTest;
import tests.products.GetProductsTest;
import tests.products.PostAddProductsTest;
import tests.products.schema.GetAuthProductsSchemaTest;
import tests.users.GetUsersTest;

@SuppressWarnings("checkstyle:Indentation")
@RunWith(Categories.class)
@Categories.IncludeCategory(suite.RegressionSuiteTests.class)
@Suite.SuiteClasses({GetHealthCheckTest.class, PostAuthLoginTest.class, GetAuthProductsSchemaTest.class,
        GetProductsTest.class, PostAddProductsTest.class, GetUsersTest.class}
)
public class RegressionSuiteTests {
}
