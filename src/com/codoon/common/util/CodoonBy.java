package com.codoon.common.util;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CodoonBy extends By {

    @Override
    public List<WebElement> findElements(SearchContext searchContext) {
        return null;
    }
}
