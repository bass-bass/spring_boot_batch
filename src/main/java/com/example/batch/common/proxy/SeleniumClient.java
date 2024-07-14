package com.example.batch.common.proxy;

import java.io.Closeable;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This version of ChromeDriver only supports Chrome version 114
 */
public class SeleniumClient implements Closeable {

	private final WebDriver driver;

	public SeleniumClient(String userAgent) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--no-sandbox");
		options.addArguments("--window-size=1600,800");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-javascript");
		options.addArguments("--disable-extensions");
		options.addArguments("--renderer-process-limit=1");
		options.addArguments("--blink-settings=imagesEnabled=false");
		options.addArguments("--user-agent=" + userAgent);
		// no need to wait for all resources to download
		options.setPageLoadStrategy(PageLoadStrategy.NONE);

		this.driver = new ChromeDriver(options);
	}

	public String request(String targetURL) throws Exception {
		//String decodeURL = URLDecoder.decode(targetURL, StandardCharsets.UTF_8);
		return scrapeYoutubeWatch(targetURL);
	}

	public synchronized String scrapeYoutubeWatch(String targetURL) throws Exception {
		driver.get(targetURL);
		String title = driver.getTitle();
		// wait up to 5 secs to get element
		WebElement parent = new WebDriverWait(driver, 5).until(d -> d.findElement(By.id("parent")));
		WebElement child = parent.findElement(By.id("child"));
		WebElement button = child.findElement(By.id("button")).findElement(By.tagName("tag"));
		Actions actions = new Actions(driver);
		actions.click(child).perform();
		String content = button.getText();
		return title + " : " + content;
	}

	@Override
	public void close() {
		if (driver != null)
			driver.quit();
	}
}
