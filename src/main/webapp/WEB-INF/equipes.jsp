<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Equipes</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page équipes </h1>
		<br />
		
		<div class="gestionEquipe">
			<form method="post" action="etudiants">
				<h3>Nombre d'équipes : </h3>
				<input type="number" id="nbEquipe" name="nbEquipe" min="0" max="12" value="${ listeEquipes.size() }"> 
				
				<input type="submit" name="submitNbEquipes" value="Valider le nombre d'équipes"/>
			</form>
		</div>
		<br />
		<div>
			<form method="post" action="etudiants">
				<input type="submit" name="submitEquipesAleatoire" value="Générer aléatoirement des équipes"/>
			</form>	
			<p style="color:red;"><c:out value="${ erreurGenererEquipes }" /></p>		
		</div>
		
		<br />
		<hr>
		<br />
		
		<div class="etudiantSansEquipe">
			<h3> Etudiants sans équipe : </h3>
			<c:if test="${ !listeEtudiants.isEmpty() }">
				<ul>
					<c:forEach items="${ listeEtudiants }" var="i">
						<c:if test="${ i.getNumeroEquipe() == -1 }">
							<li>
								<p> 
									<c:out value="${ i.getPrenom() }" /> 
									<c:out value="${ i.getNom().toUpperCase() }" /> 
									<c:out value="(${ i.getGenre() })" />
									 - 
									<c:out value="${ i.getFormationPrecedente() }" />
									<c:out value="${ i.getSitePrecedent() }" />
								</p>
							</li>
						</c:if> 
					</c:forEach>
				</ul>
			</c:if>
		</div>
		
		<br />
		<hr>
		<br />
		
		<h3>Equipes :</h3>
		<div class="listeEquipes" style="display:flex; gap:50px; flex-wrap: wrap;"> 
			<c:if test="${ listeEquipes.size() > 0 }"> 
				<c:forEach items="${ listeEquipes }" var="equipe">
					<div>
						<form method="post" action="etudiants">
							<input name="numeroEquipe" value="${ equipe.getNumero() }" hidden />
							<input style="font-weight:bold;" type="text" name="nomEquipe" id="nomEquipe" value="<c:out value="${ equipe.getNom() }" />"> 
								<c:forEach items="${ listeEtudiants }" var="etudiant">
									<c:if test="${etudiant.getNumeroEquipe() == equipe.getNumero()}">
										<p> 
											<c:out value="${ etudiant.getNom().toUpperCase() }  ${ etudiant.getPrenom() }" /> 
											<button type="submit" name="supprimerEtudiant" value="${etudiant.getId()}">X</button>
										</p>
									</c:if>
								</c:forEach>
							<c:if test="${ equipe.getEtudiants().size() < 1 }">
								<br>
								<br>
							</c:if>
							<select name="ajouterEtudiantEquipe" id="ajouterEtudiantEquipe">
								<option value="null">--Selectionner un étudiant--</option>
								<c:forEach items="${ listeEtudiants}" var="etudiantSansEquipe">
									<c:if test="${ etudiantSansEquipe.getNumeroEquipe() == -1 }">
										<option value="${ etudiantSansEquipe.getId() }"> 
											<c:out value="${ etudiantSansEquipe.getPrenom() } ${ etudiantSansEquipe.getNom().toUpperCase() }" /> 
										</option>
									</c:if>
								</c:forEach>
							</select>
							<br>
							<br>
							<input type="submit" name="validerEquipe" value="Valider l'équipe"/>
						</form>
					</div>
				</c:forEach>
			</c:if>
		</div>
		
		<br />
		<hr>
		<br />
		
		<h3>Exporter les équipes : </h3>
		<button type="button"> Exporter les équipes </button>
		
		
	</body>
</html>