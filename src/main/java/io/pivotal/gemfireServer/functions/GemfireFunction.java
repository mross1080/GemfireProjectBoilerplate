  package io.pivotal.gemfireServer.functions;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.cache.Cache;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.execute.FunctionAdapter;
import com.gemstone.gemfire.cache.execute.FunctionContext;
import com.gemstone.gemfire.cache.execute.FunctionException;
import com.gemstone.gemfire.cache.execute.RegionFunctionContext;

import java.util.Properties;

/**
 * Pivotal Data Engineering
 */
public class GemfireFunction extends FunctionAdapter
    implements Declarable {

  private static final long serialVersionUID = 1L;

  public static final String ID = "myGemfireFunction";

  private transient Cache cache = CacheFactory.getAnyInstance();

  private transient LogWriter logger = CacheFactory.getAnyInstance()
      .getDistributedSystem().getLogWriter();

  public void execute(FunctionContext context) {
    if (!(context instanceof RegionFunctionContext)) {
      throw new FunctionException(
          "This is a data aware function, and has to be called using FunctionService.onRegion.");
    }

    try {

      logger.info("Successfully Executed a Function");
      context.getResultSender().lastResult("Successfully Executed a Function");

    } catch (Exception e) {
      StringBuffer sb = new StringBuffer();
      if (e.getMessage() != null) {
        sb.append(e.getMessage() + "\n");
      }
      for (StackTraceElement ste : e.getStackTrace()) {
        sb.append(ste.toString());
        sb.append("\n");
      }
      logger.error(sb.toString());
      context.getResultSender().lastResult(sb.toString());
    }
    logger.info("I am in the " + this.getClass().getName() + " function");
    context.getResultSender().lastResult("");
  }



  public String getId() {
    return getClass().getSimpleName();
  }

  /*
   * (non-Javadoc)
   * @see com.gemstone.gemfire.cache.execute.Function#optimizeForWrite()
   */
  public boolean optimizeForWrite() {
    return false;
  }

  /*
   * (non-Javadoc)
   * @see com.gemstone.gemfire.cache.execute.Function#isHA()
   */
  public boolean isHA() {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see com.gemstone.gemfire.cache.execute.Function#hasResult()
   */
  public boolean hasResult() {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see com.gemstone.gemfire.cache.Declarable#init(java.util.Properties)
   */
  public void init(final Properties properties) {
  }
}
