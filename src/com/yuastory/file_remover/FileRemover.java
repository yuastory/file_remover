package com.yuastory.file_remover;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;

public class FileRemover extends JFrame {
	private static final long serialVersionUID = 1L;

	public FileRemover() {
		setSize(600, 400);
		setResizable(false);
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel lbl = new JLabel("Drag Here.");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("gulim", Font.BOLD, 16));
		getContentPane().add(lbl, BorderLayout.CENTER);

		new FileDrop(getContentPane(), new FileDrop.Listener() {
			@Override
			public void filesDropped(File[] files) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						for (File fileEntry : files)
							delete(fileEntry);
						JOptionPane.showMessageDialog(null, "Deleted.");
					}

				});
			}
		});
	}

	void delete(File dirToDelete) {
		if (!dirToDelete.isDirectory())
			dirToDelete.delete();
		else
			try {
				FileUtils.deleteDirectory(dirToDelete);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		FileRemover fileRemover = new FileRemover();
		fileRemover.setVisible(true);
	}
}
