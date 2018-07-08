package com.fish.notes;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.fish.notes.Notes;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Notes notes = new Notes();
		initialize(notes, config);
		Notes.setPlatformResolver(new AndroidResolver(notes, this));
		}
	}


