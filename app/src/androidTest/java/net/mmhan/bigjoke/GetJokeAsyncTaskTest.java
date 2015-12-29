package net.mmhan.bigjoke;

import junit.framework.TestCase;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mmhan on 29/12/15.
 */
public class GetJokeAsyncTaskTest extends TestCase {


    String mResult = null;
    CountDownLatch signal = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        signal = new CountDownLatch(1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        signal.countDown();
    }

    public void testGetJokeAsyncTask() throws Exception {
        new GetJokeAsyncTask().execute(new GetJokeAsyncTask.GetJokeAsyncTaskListener(){
            @Override
            public void onComplete(String result) {
                mResult = result;
                signal.countDown();
            }
        });
        signal.await();
        assertNotNull(mResult);
    }
}