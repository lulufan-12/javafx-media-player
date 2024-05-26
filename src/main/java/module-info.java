module online.luismartinsdev.mediaplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;
    requires java.desktop;
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.jcl;
    requires spring.aop;
    requires spring.expression;
    requires lombok;

    opens online.luismartinsdev.mediaplayer;
    opens online.luismartinsdev.mediaplayer.context;
    opens online.luismartinsdev.mediaplayer.controller;
    opens online.luismartinsdev.mediaplayer.util;
    opens online.luismartinsdev.mediaplayer.service;
    opens online.luismartinsdev.mediaplayer.config;
    opens online.luismartinsdev.mediaplayer.annotation.stereotype;
    opens online.luismartinsdev.mediaplayer.view.scene;
    opens online.luismartinsdev.mediaplayer.view.pane;
    opens online.luismartinsdev.mediaplayer.model;
    opens online.luismartinsdev.mediaplayer.view.control.button;
    opens online.luismartinsdev.mediaplayer.view.control.listview;
    opens online.luismartinsdev.mediaplayer.state;
    opens images.icons;

    exports online.luismartinsdev.mediaplayer;
    exports online.luismartinsdev.mediaplayer.annotation.stereotype;
    exports online.luismartinsdev.mediaplayer.bean;
    opens online.luismartinsdev.mediaplayer.bean;
}