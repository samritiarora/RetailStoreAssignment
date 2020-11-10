package enterprise.org.assignment.exception;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ServiceExceptionTest {
    @Test
    public void testServiceException() throws Exception {
        ServiceException exception = new ServiceException("testService", new Throwable("invalid service exception"));
        assertThat(exception.getMessage(), containsString("testService"));
    }

    @Test
    public void testServiceExceptionWithMessage() {
        ServiceException exception = new ServiceException("testService");
        assertThat(exception.getMessage(), containsString("testService"));
    }
}
