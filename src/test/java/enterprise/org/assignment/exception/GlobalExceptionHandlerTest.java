package enterprise.org.assignment.exception;

import enterprise.org.assignment.exception.model.ErrorResponseModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest
{
    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    public void testIllegalArgumentExceptionHandler()
    {
        ResponseEntity<ErrorResponseModel> responseEntity = globalExceptionHandler.illegalArgumentExceptionHandler(new
                IllegalArgumentException("customMessageCode"));
        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertEquals("customMessageCode", responseEntity.getBody().getMessage());
        Assert.assertEquals("customMessageCode", responseEntity.getBody().getCode());
    }

    @Test
    public void testGenericExceptionHandler()
    {
        ResponseEntity<ErrorResponseModel> responseEntity = globalExceptionHandler.genericExceptionHandler(new Exception
                ("someMessage"));
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        Assert.assertEquals("someMessage", responseEntity.getBody().getMessage());
        Assert.assertEquals("UNKNOWN", responseEntity.getBody().getCode());
    }
}
