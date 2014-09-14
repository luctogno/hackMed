package net.tinvention.server.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A standard peak detector in time series.
 * <p>
 * The goal of this class is to identify peaks in a 1D time series (float[]). It
 * simply implements G.K. Palshikar's <i>Simple Algorithms for Peak Detection in
 * Time-Series</i> ( Proc. 1st Int. Conf. Advanced Data Analysis, Business
 * Analytics and Intelligence (ICADABAI2009), Ahmedabad, 6-7 June 2009), We
 * retained the first "spikiness" function he proposed, based on computing the
 * max signed distance to left and right neighbors.
 * <p>
 * 
 * <pre>
 *              http://sites.google.com/site/girishpalshikar/Home/mypublications/
 *              SimpleAlgorithmsforPeakDetectioninTimeSeriesACADABAI_2009.pdf
 * </pre>
 * 
 * @author Jean-Yves Tinevez <jeanyves.tinevez@gmail.com> May 10, 2011
 */
public class PeakDetector {

	private Float[] T;
	private float slope;
	private List<Integer> peaksPos;

	public List<Integer> getPeaksPos() {
		return peaksPos;
	}

	/**
	 * Create a peak detector for the given time series.
	 */
	public PeakDetector(final Float[] timeSeries) {
		this.T = timeSeries;
	}

	/**
	 * Return the peak locations as array index for the time series set at
	 * creation.
	 * 
	 * @param windowSize
	 *            the window size to look for peaks. a neighborhood of +/-
	 *            windowSize will be inspected to search for peaks. Typical
	 *            values start at 3.
	 * @param stringency
	 *            threshold for peak values. Peak with values lower than <code>
	 * mean + stringency * std</code> will be rejected. <code>Mean</code> and
	 *            <code>std</code> are calculated on the spikiness function.
	 *            Typical values range from 1 to 3.
	 * @return an int array, with one element by retained peak, containing the
	 *         index of the peak in the time series array.
	 */
	public void process(final int windowSize, final float stringency) {
		HashMap<Integer, Float> points = new HashMap<>();

		List<Float> extremes = new ArrayList<Float>();
		Float previous = null;
		Float previousSlope = (float) 0;

		for (int i = 0; i < T.length; i++) {
			Float p = T[i];
			if (previous == null) {
				previous = p;
				continue;
			}
			Float slope = p - previous;
			if (((slope * previousSlope) < 0) && ((previous / p) < stringency)) { // look
																					// for
																					// sign
																					// changes
				extremes.add(previous);
				points.put(i, p);
			}
			previousSlope = slope;
			previous = p;
		}

		deleteFalsiPositivi(points, windowSize);

	}

	private void deleteFalsiPositivi(HashMap<Integer, Float> points,
			int windowSizey) {
		Map.Entry<Integer, Float> maxEntry = null;

		if ((points != null) && (points.size() != 0)) {

			// Prendo il più grande
			for (Map.Entry<Integer, Float> entry : points.entrySet()) {
				if ((maxEntry == null)
						|| (entry.getValue().compareTo(maxEntry.getValue()) > 0)) {
					maxEntry = entry;
				}
			}

			peaksPos.add(maxEntry.getKey());

			List<Integer> keys = (List<Integer>) points.keySet();
			for (int i = 0; i < keys.size(); i++) {
				Integer pos = keys.get(i);
				if ((pos - maxEntry.getKey()) < Math.abs(windowSizey)) {
					points.remove(pos);
				}
			}
			deleteFalsiPositivi(points, windowSizey);
		}

	}
}
