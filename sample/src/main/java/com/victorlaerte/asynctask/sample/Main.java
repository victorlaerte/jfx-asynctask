package com.victorlaerte.asynctask.sample;

import com.victorlaerte.asynctask.AsyncTask;
import java.io.File;
import java.net.URI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Victor Oliveira
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		URI uri = new File("sample/src/main/java/com/victorlaerte/asynctask/sample/sample.fxml").toURI();
		Parent root = FXMLLoader.load(uri.toURL());

		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();

		MyAsyncTask myAsyncTask = new MyAsyncTask();
		myAsyncTask.setDaemon(false);
		myAsyncTask.execute();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

		@Override
		public void onPreExecute() {
			System.out.println("Background Thread will start");
		}

		@Override
		public Boolean doInBackground(String... params) {
			System.out.println("Background Thread is running");

			int i = 0;
			while (i < 5) {
				progressCallback(i);
				i++;

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					return false;
				}
			}

			return true;
		}

		@Override
		public void onPostExecute(Boolean success) {
			System.out.println("Background Thread has stopped");

			if (success) {
				System.out.println("Done with success");
			} else {
				System.out.println("Done with error");
			}
		}

		@Override
		public void progressCallback(Integer... params) {
			System.out.println("Progress " + params[0]);
		}
	}
}
