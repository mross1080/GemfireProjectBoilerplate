package io.pivotal.gemfireServer.listeners;

import com.gemstone.gemfire.LogWriter;
import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.RegionEvent;
import com.gemstone.gemfire.cache.util.CacheListenerAdapter;

import java.util.Properties;

/**
 * Pivotal Data Engineering
 */

public class CacheListener extends CacheListenerAdapter<String, String>
    implements Declarable {

  private static LogWriter logger;

  static {
    logger = CacheFactory.getAnyInstance().getDistributedSystem()
        .getLogWriter();
  }

  public CacheListener(){}

  @Override
  public void afterCreate(EntryEvent<String, String> entryEvent) {
    String key = entryEvent.getKey();
    logger.info("key=" + entryEvent.getKey());
    logger.info("value=" + entryEvent.getNewValue());

  }

  @Override
  public void afterUpdate(EntryEvent<String, String> entryEvent) {
    logger.info("key=" + entryEvent.getKey());
    logger.info("value=" + entryEvent.getNewValue());
  }

  @Override
  public void afterDestroy(EntryEvent<String,String> event) {
  }

  @Override
  public void afterInvalidate(EntryEvent<String,String> event) {
  }

  @Override
  public void afterRegionDestroy(RegionEvent<String,String> event) {
  }

  @Override
  public void afterRegionCreate(RegionEvent<String,String> event) {
  }

  @Override
  public void afterRegionInvalidate(RegionEvent<String,String> event) {
  }

  @Override
  public void afterRegionClear(RegionEvent<String,String> event) {
  }

  @Override
  public void afterRegionLive(RegionEvent<String,String> event) {
  }
  
  
  @Override
  public void init(Properties props) {
    logger.info("I am in " + this.getClass().getSimpleName() + " class init");
  }

}
