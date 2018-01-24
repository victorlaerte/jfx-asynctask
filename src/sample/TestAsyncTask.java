package sample;

import com.victorlaerte.asynctask.AsyncTask;

/**
 * @author Victor Oliveira
 */
class TestAsyncTask extends AsyncTask<String, Integer, Boolean> {

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
