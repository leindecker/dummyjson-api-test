package runners;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.healthCheck.schema.HealtCheckSchemaTest;
import tests.login.schema.PostAuthSchemaTest;
import tests.products.schema.GetAuthProductsSchemaTest;
import tests.products.schema.PostAddProductsSchemaTest;
import tests.users.schema.GetUsersSchemaTest;

@SuppressWarnings("checkstyle:Indentation")
@RunWith(Categories.class)
@Categories.IncludeCategory(suite.ContractSuiteTests.class)
@Suite.SuiteClasses({ HealtCheckSchemaTest.class, PostAuthSchemaTest.class, GetAuthProductsSchemaTest.class,
        PostAddProductsSchemaTest.class, GetUsersSchemaTest.class}
)
public class ContractSuiteTests {
}