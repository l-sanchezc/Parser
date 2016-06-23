package edu.iit.lazaro.parser;

import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.app.ProgressDialog;

public class MainActivity extends Activity {
    XMLGettersSetters data;
    ProgressDialog waitProgress;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new BackgroundTask().execute();
    }

    public class BackgroundTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (waitProgress != null) {
                waitProgress.dismiss();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (waitProgress != null) {
                waitProgress.dismiss();
            } else {
                setLayout();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                synchronized (this) {
                    saxParser();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    private void saxParser() {
        try {
            /**
             * Create a new instance of the SAX parser
             **/
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            SAXParser saxP = saxPF.newSAXParser();
            XMLReader xmlR = saxP.getXMLReader();

            // URL of the XML
            URL url = new URL("http://www.papademas.net/cd_catalog3.xml");

            /**
             * Create the Handler to handle each of the XML tags.
             **/
            XMLHandler myXMLHandler = new XMLHandler();
            xmlR.setContentHandler(myXMLHandler);
            xmlR.parse(new InputSource(url.openStream()));

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    //set up the different views, add them to the groupView and put everything in the layout
    private void setLayout() {

        data = XMLHandler.data;
        int[] colorArray = getResources().getIntArray(R.array.colorArray);
        int soldOutCount = 0;

        /**
         * Get the view of the layout within the main layout, so that we can
         add TextViews.
         **/
        View mainLayout = findViewById(R.id.mainLayout);
        View layout = findViewById(R.id.layout);

        /**
         * Create TextView Arrays to add the retrieved data to.
         **/
        TextView title[];
        TextView artist[];
        TextView country[];
        TextView company[];
        TextView price[];
        TextView year[];
        TextView CD[];
        TextView soldOut;

        /**
         * Makes the TextView length the size of the TextView arrays by
         getting the size of the
         **/
        title = new TextView[data.getTitle().size()];
        artist = new TextView[data.getArtist().size()];
        country = new TextView[data.getCountry().size()];
        company = new TextView[data.getCompany().size()];
        price = new TextView[data.getPrice().size()];
        year = new TextView[data.getYear().size()];
        CD = new TextView[data.getAttribute().size()];
        soldOut = new TextView(this);

        /**
         * Run a for loop to set All the TextViews with text until
         * the size of the array is reached.
         *
         **/
         for (int i = 0; i < data.getTitle().size(); i++) {
             title[i] = new TextView(this);
             title[i].setBackgroundColor(colorArray[i]);
             title[i].setText("Title = " + data.getTitle().get(i));
             artist[i] = new TextView(this);
             artist[i].setBackgroundColor(colorArray[i]);
             artist[i].setText("Artist = " + data.getArtist().get(i));
             country[i] = new TextView(this);
             country[i].setBackgroundColor(colorArray[i]);
             country[i].setText("Country = " + data.getCountry().get(i));
             company[i] = new TextView(this);
             company[i].setBackgroundColor(colorArray[i]);
             company[i].setText("Company = " + data.getCompany().get(i));
             price[i] = new TextView(this);
             price[i].setBackgroundColor(colorArray[i]);
             price[i].setText("Price = " + data.getPrice().get(i));
             year[i] = new TextView(this);
             year[i].setBackgroundColor(colorArray[i]);
             year[i].setText("Year = " + data.getYear().get(i));
             CD[i] = new TextView(this);
             CD[i].setText("Sold out = " + data.getAttribute().get(i));
             CD[i].setBackgroundColor(colorArray[i]);
             if (data.getAttribute().get(i).matches("yes")) soldOutCount++;
             ((ViewGroup) layout).addView(title[i]);
             ((ViewGroup) layout).addView(artist[i]);
             ((ViewGroup) layout).addView(country[i]);
             ((ViewGroup) layout).addView(company[i]);
             ((ViewGroup) layout).addView(price[i]);
             ((ViewGroup) layout).addView(year[i]);
             ((ViewGroup) layout).addView(CD[i]);
        }
        soldOut.setText("CD's that were sold out: " + soldOutCount);
        soldOut.setBackgroundColor(Color.GRAY);
        ((ViewGroup) layout).addView(soldOut);
        setContentView(mainLayout);
    }

}
