package com.rabidgremlin.mutters.bot.ink;

import com.bladecoder.ink.runtime.Story;

/**
 * 
 * This is a wrapper class that overrides the {@link #bindExternalFunction(String, ExternalFunction)} method so that we
 * can bind external functions without unbinding them first.
 * 
 * @author Joe Dai
 *
 */
public class StoryDecorator
    extends Story
{

  public StoryDecorator(String jsonString)
      throws Exception
  {
    super(jsonString);
  }

  /**
   * Binds the given external function. The function with the same name will be unbound first.
   */
  @Override
  public void bindExternalFunction(String funcName, ExternalFunction func)
    throws Exception
  {
    try
    {
      super.unbindExternalFunction(funcName);
    }
    catch (Exception e)
    {
      if (e.getMessage().contains("has not been bound"))
      {
        // Intentional blank, if we get this it means the function has never been bound.
      }
      else
      {
        throw e;
      }
    }
    super.bindExternalFunction(funcName, func);
  }

}
