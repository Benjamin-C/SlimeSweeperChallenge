package dev.benjaminc.challenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import dev.benjaminc.slimesweeper.SlimeSweeper;
import util.Logger;

public class Main {
	
	/** A scanner anyone can use for System.in */
	public static Scanner scan;
	
	private static int width = 8;
	private static int height = 8;
	private static float slimePercent = 0.05f;
	private static int runCount = 2;
	
	private static List<SlimeStatistic> stats;
	
	public static void main(String args[]) {
		Logger mainLogger = new Logger(false, "");
		mainLogger.start();
		
		scan = new Scanner(System.in);
		
		Robot r = null;
		
		// Load Robot
		switch(args.length) {
		case 1: { r = loadRobotsFromJar(args[0]); } break;
		case 3: { loadBoardSetting(args[0], args[1], args[2]); } break;
		case 4: { r = loadRobotsFromJar(args[0]); loadBoardSetting(args[1], args[2], args[3]); } break;
		}
		
		if(r == null) {
			System.out.println("Using default robot");
			r = new DefaultRobot();
		}
			
		System.out.println("Press enter to start");
		scan.hasNextLine();
		
		stats = new ArrayList<SlimeStatistic>();
		
		mainLogger.pause();
		
		for(int run = 0; run < runCount; run++) {
			// Play the game
			if(r != null) {
				
				SlimeSweeper ss = new SlimeSweeper(width, height, slimePercent);
				List<RobotAction> actions = new ArrayList<RobotAction>();
				
				Logger runLogger = new Logger(false, "r" + run);
				runLogger.start();
				
				int nulls = 0;
				int moves = 0;
				try {
					r.init();
				} catch(Exception e) {
					System.out.println("Your robot generated an exception during init.");
					e.printStackTrace();
					stats.add(new SlimeStatistic(ss, actions, e));
					continue;
				}
				
				do {
					try {
						RobotAction ro = r.makeMove(ss.getGameBoard());
						actions.add(ro);
						moves++;
						if(ro == null) {
							nulls++;
							if(nulls >= 8) {
								throw new NullPointerException("The robot returned null " + nulls + " times in a row.");
							}
						} else {
							nulls = 0;
							ss.runRobotActoin(ro);
						}
					} catch(Exception e) {
						System.out.println("Your robot generated an exception during its turn.");
						e.printStackTrace();
						stats.add(new SlimeStatistic(ss, actions, e));
						continue;
					}
					ss.printBoard();
				} while(!ss.isDone());
				
				System.out.println("You finished after " + moves + " moves");
				
				try {
					r.cleanup(ss.getSteppedSlimes(), ss.getGameBoard());
				} catch(Exception e) {
					System.out.println("Your robot generated an exception during cleanup.");
					e.printStackTrace();
					stats.add(new SlimeStatistic(ss, actions, e));
					continue;
				}
				stats.add(new SlimeStatistic(ss, actions, null));
				
				runLogger.stop();
			}
		}
		
		mainLogger.resume();
		
		for(SlimeStatistic s : stats) {
			s.print();
		}
		
		mainLogger.stop();
		
		exit(0);
	}
	
	private static void exit(int code) {
		scan.close();
		System.exit(1);
	}
	
	private static void loadBoardSetting(String inWidth, String inHeight, String inSlimePercent) {
		// Width
		try {
			width = Integer.parseInt(inWidth);
		} catch(NumberFormatException e) {
			System.out.println("Width is not a number, using default value");
		}
		// Height
		try {
			height = Integer.parseInt(inHeight);
		} catch(NumberFormatException e) {
			System.out.println("Height is not a number, using default value");
		}
		// SlimePercent
		try {
			slimePercent = Float.parseFloat(inSlimePercent);
		} catch(NumberFormatException e) {
			System.out.println("SlimePercent is not a number, using default value");
		}
	}
	
	private static Robot loadRobotsFromJar(String jarname) {
		System.out.println("Loading " + jarname);
		List<Class<?>> robots = new ArrayList<Class<?>>();
		try {
			String pathToJar = jarname;
			JarFile jarFile = new JarFile(pathToJar);
			Enumeration<JarEntry> e = jarFile.entries();
	
			URL[] urls = { new URL("jar:file:" + pathToJar+"!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			
			while (e.hasMoreElements()) {
			    JarEntry je = e.nextElement();
			    if(je.isDirectory() || !je.getName().endsWith(".class")){
			        continue;
			    }
			    // -6 because of .class
			    
			    String className = je.getName().substring(0,je.getName().length()-6);
			    
		    	className = className.replace('/', '.');
			    Class<?> clazz = cl.loadClass(className);
			    
			    robots.add(clazz);
			}
			jarFile.close();
		} catch (IOException | ClassNotFoundException e1) {
			if(e1 instanceof FileNotFoundException) {
				System.out.println("The specified file does not exist.");
			} else {
				e1.printStackTrace();
			}
		}
		
		Class<?> roboClazz = null;
		
		// Make sure there is a valid robot
		if(robots.size() == 1) {
			roboClazz = robots.get(0);
			System.out.println("Using " + roboClazz.getName());
		} else if(robots.size() > 1){
			for(int i = 0; i < robots.size(); i++) {
				System.out.println("[" + (i+1) + "] " + robots.get(i).getName());
			}
			while(roboClazz == null) {
				System.out.println("Which robot do you want to use? >");
				String in = scan.nextLine();
				try {
					int num = Integer.parseInt(in);
					roboClazz = robots.get(num-1);
				} catch(NumberFormatException e) {
					System.out.println("That's not a number. Please try again.");
				} catch(IndexOutOfBoundsException e) {
					System.out.println("That number is out of range. Please try again.");
				}
			}	
		}
		
		Robot r = null;
				
		if(roboClazz != null) {
			Object newobj = null;
	
			try {
				Constructor<?> ctor = roboClazz.getConstructor();
				newobj = ctor.newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException err) {
				err.printStackTrace();
			}
			
			if(newobj != null && newobj instanceof Robot) {
				r = (Robot) newobj;
			}
		}
		return r;
	}
	
	public static Robot resetRobot(Robot robotToBeReset) {
		Object newobj = null;
		
		try {
			Constructor<?> ctor = robotToBeReset.getClass().getConstructor();
			newobj = ctor.newInstance();
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException err) {
			err.printStackTrace();
		}
		
		if(newobj != null && newobj instanceof Robot) {
			return (Robot) newobj;
		}
		
		return null;
	}
}
