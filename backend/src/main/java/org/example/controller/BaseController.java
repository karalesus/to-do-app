package org.example.controller;

import org.example.viewModels.BaseViewModel;

public interface BaseController {
    /**
     * Создает базовую модель представления.
     *
     * @param title заголовок страницы
     * @return базовая модель с общими данными
     */

    BaseViewModel createBaseViewModel(String title);
}