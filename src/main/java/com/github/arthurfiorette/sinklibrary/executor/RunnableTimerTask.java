package com.github.arthurfiorette.sinklibrary.executor;

import java.util.TimerTask;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

@RequiredArgsConstructor
public class RunnableTimerTask extends TimerTask {

  @Getter
  @Delegate
  private final Runnable runnable;

}
