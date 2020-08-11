package hu.szrnkapeter.monolith;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;

@PowerMockIgnore("javax.management.*")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(PowerMockRunner.class)
public abstract class AbstractServiceTest {
	
	protected static final String RESPONSE_CANNOT_NULL = "Response cannot be null!";

	@Before
	public void doBefore() {
		MockitoAnnotations.initMocks(this);
	}
}