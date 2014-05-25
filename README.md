AsyncTask
=========

This class was created to simplify how to handle Thread tasks in Javafx, and it is based on the same idea of AsyncTask from Android.

AsyncTask enables proper and easy use of the UI thread. This class allows to perform background operations and publish results on the UI thread without having to manipulate threads.

An asynchronous task is defined by a computation that runs on a background thread and whose result is published on the UI thread. An asynchronous task is defined by extending the class, and 4 steps, called onPreExecute, doInBackground, progressCallback and onPostExecute.

Optionally you have the method setDaemon to set your threads daemon, which means that if your javafx application has been closed it can still running or not. setDamon(boolean) can only be called before the thread has been started. By default the thread is set to daemon.

Methods
=========

onPreExecute - is used to run some rotine before the background task has started

doInBackground - is used to perform background tasks

onPostExecute - is used to run some finally rotine after background task has done

progressCallback - it will be called every time you call publishProgress to update your UI Thread as you want

=========

publishProgress - is used to call your progressCallback and update your UI component

setDaemon - is used to set your thread daemon

execute - called to initiate all process

interrupt - is called to interrupt your thread process

Example of use:
=========

    public class Example extends AsyncTask {
        private UIController controller;
    
        public Example(UIController controller) {
            this.controller = controller;
        }
    
        @Override
        void onPreExecute() {
        
            //This method runs on UI Thread before background task has started
            this.controller.updateProgressLabel("Starting Download")
        }

        @Override
        void doInBackground() {
    
        //This method runs on background thread
        
        boolean downloading = true;
        
            while (downloading){
            
                /*
                * Your download code
                */
                
                double progress = 65.5 //Your progress calculation 
                publishProgress(progress);
            }
        }

        @Override
        void onPostExecute() {
    
            //This method runs on UI Thread after background task has done
            this.controller.updateProgressLabel("Download is Done");
    
        }

        @Override
        void progressCallback(Object... params) {
        
            //This method update your UI Thread during the execution of background thread
            
            double progress = (double)params[0]
            this.controller.updateProgress(progress);
        }
    }

    //To call this class you just need to instatiate that doing 
    
    Example testing = new Example(myController);
    testing.execute();
