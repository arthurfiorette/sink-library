package com.github.arthurfiorette.sinklibrary.executor;

import java.util.TimerTask;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RunnableTimerTask extends TimerTask {

  @Getter
  private final Runnable runnable;

  @Override
  public void run() {
    runnable.run();
  }

}
