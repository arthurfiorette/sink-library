package com.github.arthurfiorette.sinklibrary.interfaces;

public interface MultiComponent<T extends BaseComponent> extends BaseComponent {

  Class<? super T> getRegistrationClass();
  
  T getComponent();
  
}