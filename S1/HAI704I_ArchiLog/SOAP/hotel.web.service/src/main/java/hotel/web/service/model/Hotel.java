package hotel.web.service.model;

import java.util.ArrayList;

import javax.jws.WebService;

import hotel.web.service.services.IHotelService;

@WebService(endpointInterface="hotel.web.service.services.IHotelService")
public class Hotel implements IHotelService
{

	private String nom;
	private Adresse adresse;
	private Integer etoiles;
	
	private ArrayList<Agence> partenaires;
	private ArrayList<Chambre> chambres;

	public Hotel(String nom, Adresse adresse, Integer etoiles)
	{
		this.nom = nom;
		this.adresse = adresse;
		this.etoiles = etoiles;
		this.chambres = new ArrayList<Chambre>();
		this.partenaires = new ArrayList<Agence>();
	}
	
	@Override
	public String reserver(String nomChambre, String nom, String prenom, double carte, String debut, String fin)
	{
		Chambre c = null;
		
		for (Chambre chambre : chambres)
		{
			if (chambre.getNom().equalsIgnoreCase(nomChambre))
			{
				c = chambre;
			}
		}
		
		if (c != null)
		{
			c.getReservation().add(new Reservation(new  Client(nom, prenom, carte), debut, fin)) ;
			return "La chambre a ete reservee.\n " + nom + " " + prenom + " " + carte + " " + nomChambre;
		}
		else
		{
			return "Une erreur est survenue";
		}
	}	

	public Integer getReduction(String login, String mdp)
	{
		Integer reduc = 0;
		
		for (Agence p : partenaires)
		{
			if (p.getNomAgence().equalsIgnoreCase(login) && p.getMotdepasse().equals(mdp))
			{
				reduc = p.getReduction();
			}
		}	
		
		return reduc;
	}
	
	public ArrayList<String> rechercher(String login, String mdp, String dateArr, String dateDep, int prixMin, int prixMax, int lits)
	{
		Integer reduction = 0;
		
		for (Agence p : partenaires)
		{
			if (p.getNomAgence().equalsIgnoreCase(login) && p.getMotdepasse().equals(mdp))
			{
				reduction = p.getReduction();
			}
		}
		
		ArrayList<String> offres = new ArrayList<String>();
		ArrayList<Chambre> resRecherche = rechercherChambres(dateArr, dateDep, prixMin, prixMax, lits, reduction);
		
		for(Chambre c  : resRecherche)
		{
			Offre o = new Offre(this, c, c.getPrix(reduction));
			offres.add(o.toString());
		}
		
		return offres;
	}
	
	//Date au format aaaa/mm/jj
	private ArrayList<Chambre> rechercherChambres(String dateArr, String dateDep, int prixMin, int prixMax, int lits, int reduction)
	{
		ArrayList<Chambre> chambres = new ArrayList<Chambre>();
		
		for (Chambre c : chambres)
		{
			if ((c.getPrix(reduction) >= prixMin) && (c.getPrix(reduction) <= prixMax) && (c.getNbLits() >= lits))
			{
				int ddeb = Integer.parseInt(dateArr.replace("/", ""));
				int dfin = Integer.parseInt(dateDep.replace("/", ""));
				
				boolean valid = true;
				
				for (int i = 0; i < c.getReservation().size(); i++)
				{
					int cdeb = Integer.parseInt(c.getReservation().get(i).getDebut().replace("/", ""));
					int cfin = Integer.parseInt(c.getReservation().get(i).getFin().replace("/", ""));
					
					if ((ddeb > cdeb && ddeb < cfin) || (dfin < cfin && dfin > cdeb))
					{
						valid = false;
					}
				}
				
				if (valid)
				{
					chambres.add(c);
				}
			}
		}
		
		return chambres;
	}
	
	
	public String toString()
	{
		return "[nom=" + nom + ", addresse=" + adresse + ", etoiles=" + etoiles + ", partenaires=" + partenaires + ", chambres=" + chambres + "]";
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public String getAddresse()
	{
		return adresse.getVille();
	}
	
	public Integer getEtoiles()
	{
		return etoiles;
	}
	
	public ArrayList<Chambre> getChambres()
	{
		return chambres;
	}
	
	public ArrayList<Agence> getPartenaires()
	{
		return partenaires;
	}
}