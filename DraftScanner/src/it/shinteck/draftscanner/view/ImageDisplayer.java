package it.shinteck.draftscanner.view;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/*
 * $Id$
 */

public class ImageDisplayer extends Component {
	private Image image;
	private String title;
	private Frame frame;

	/**
	 * Create an ImageDisplayer with an Image.
	 * @param image
	 */
	public ImageDisplayer(Image image) {
		this.image = image;

		frame = new Frame();

		frame.addWindowListener(listener);

		ScrollPane scroller = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		scroller.add(this);
		scroller.setBackground(Color.lightGray);

		frame.add("Center", scroller);

		if(image != null)
			frame.setSize(new Dimension(image.getWidth(null), image.getHeight(null)));

		if(frame.getHeight() < 600 || frame.getWidth() < 400)
			frame.setSize(new Dimension(600, 412));
                

		frame.setVisible(true);
		frame.toFront();
	}


	public Frame getFrame() {
		return frame;
	}

	/**
	 * Create an Image Displayer with specific title and image.
	 * @param title
	 * @param image
	 */
	public ImageDisplayer(String title, Image image) {
		this(image);
		setTitle(title);
	}

    @Override
	public Dimension getPreferredSize() {
		if(image == null || image.getHeight(null) <= 0)
			return new Dimension(300, 300);
		else
			return new Dimension(image.getWidth(null), image.getHeight(null));
	}

	/**
	 * Override paint method.
	 */
    @Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		if (image != null && image.getHeight(null) > 0) {
			g.drawImage(image, 0, 0, 800, 600, this);
		}else{
			g.drawString("Oops! NO IMAGE LOADED. ", 20, 20);
		}
	}

	public void setTitle(String title) {
		this.title = title;
		if(frame != null && title != null)
			frame.setTitle(title);
	}



	WindowListener listener = new WindowAdapter() {
        @Override
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}
	};
}


