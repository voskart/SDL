package controller;

import model.Coordinates;
import model.Data;
import model.Stone;
import service.DatabaseService;
import service.WikidataService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.xquery.XQException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/HotOrNot")
public class HotOrNotController {

	@Autowired
	private DatabaseService dbservice;

	int i = 0;

	private static Logger LOG = Logger.getLogger(HotOrNotController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		String stone = ""; // hier muss der Link zu dem Bild des Steines hin
		model.addAttribute("image", stone);
		WikidataService wds = new WikidataService();
		// model.addAttribute("info", wds.getAbstract("Q744630") );
		return "HotOrNot";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String like(HttpServletRequest req, ModelMap model) {
		/*
		 * String stone = ""; //hier muss der Link zu dem Bild des Steines hin
		 * model.addAttribute("image", stone); String value =
		 * req.getParameter("x"); // Value = hot wenn gemocht. Value = not wenn
		 * nicht emocht
		 */

		// Generate sample objects for the presentation

		Data data1 = new Data();
		data1.set_abstract("Als Migmatit wurde ursprünglich ein Gestein bezeichnet, welches im Aufschlussmassstab aus zwei oder mehr petrographisch unterscheidbaren Teilen besteht. Dabei weist ein Teil Merkmale eines metamorphen, ein anderer (meist heller) Teil Merkmale eines magmatischen Gesteins auf. Heute versteht man darunter ein partiell aufgeschmolzenes Gestein (oder Anatexit), der helle magmatische Teil stellt die ehemalige, wieder erstarrte Gesteinsschmelze dar");
		data1.setCoordinates(new Coordinates(48.9999995, 12.6667));
		data1.setUrl("http://www.skan-kristallin.de/bornholm/gesteine/gesteinsdarstellung/paradisbakke/l_paradisbakke/l_bertelegard-migmatit.jpg");

		Data data2 = new Data();
		data2.set_abstract("Basalt ist ein basisches (SiO2-armes) Ergussgestein. Es besteht vor allem aus einer Mischung von Eisen- und Magnesium-Silikaten mit Pyroxen und calciumreichem Feldspat (Plagioklas) sowie meist auch mit Olivin. Basalt ist das vulkanische Äquivalent zum Gabbro (Plutonit), der die gleiche chemische Zusammensetzung hat.");
		data2.setCoordinates(new Coordinates(50.465, 10.0865));
		data2.setUrl("http://www.mineralienkrause.de/assets/images/basalt3.jpg");

		Data data3 = new Data();
		data3.set_abstract("Stonehenge ist ein in der Jungsteinzeit errichtetes und mindestens bis in die Bronzezeit genutztes Bauwerk in der Nähe von Amesbury in Wiltshire, England, etwa 13 Kilometer nördlich von Salisbury. Es besteht aus einer Grabenanlage, die von einer aus mehreren konzentrischen Steinkreisen gebildeten Megalithstruktur umgeben ist.");
		data3.setCoordinates(new Coordinates(51.178844, -1.826189));
		data3.setUrl("https://upload.wikimedia.org/wikipedia/commons/3/35/Stonehenge_on_27.01.08.jpg");

		ArrayList<Data> dataArrayList = new ArrayList<Data>();
		dataArrayList.add(data1);
		dataArrayList.add(data2);
		dataArrayList.add(data3);

		model.addAttribute("info", dataArrayList.get(i % 3).get_abstract());
		model.addAttribute("coords", dataArrayList.get(i % 3).getCoordinates());
		model.addAttribute("img", dataArrayList.get(i % 3).getUrl());

		i = i + 1;

//		Stone stone = getNextStone();
//		if(stone != null){
//			fillModel(model, stone);
//		}else{			
//			HotOrNotController.LOG.error("Es konnte kein neuer Stein ermittelt werden.");
//		}

		return "HotOrNot";
	}
	
	private void fillModel(ModelMap model, Stone stone) {
		model.addAttribute("info", stone.getTitel()); // FIXME
		model.addAttribute("coords", new Coordinates(51.178844, -1.826189)); // FIXME
		model.addAttribute("img", stone.getBild());
	}

	private Stone getNextStone(){
		try {
			List<Stone> stones = dbservice.getAllStones();
			if (stones != null) {
				if (i == stones.size()) {
					i = 0;
				}
				return stones.get(i);
			}
		} catch (XQException e) {
			HotOrNotController.LOG.error(e);
		} catch (IOException e) {
			HotOrNotController.LOG.error(e);
		}
		return null;
	}
}