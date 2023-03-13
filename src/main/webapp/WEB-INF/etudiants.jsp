<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Etudiants</title>
	</head>
	
	<body>
		<%@ include file="menu.jsp" %>
		<h1> Page Etudiants </h1>
		<br />
		
		<form method="POST" action="etudiants">
			<input type="text" id="nom" name="nom" placeholder="Nom"/>
			<input type="text" id="prenom" name="prenom" placeholder="Prenom" />
			<select id="genre" name="genre">
				<option value="Homme"> Homme </option>
				<option value="Femme"> Femme </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			
			<select id="sitePrecedent" name="sitePrecedent">
				<option value="Angers"> Angers </option>
				<option value="Paris"> Paris </option>
				<option value="Dijon"> Dijon </option>
				<option value="Autre"> Autre </option>
			</select>
			
			<select id="formationPrecedente" name="formationPrecedente">
				<option value="E3e"> E3e </option>
				<option value="E4e"> E4e </option>
				<option value="Bachelor"> Bachelor </option>
				<option value="Apprenti"> Apprenti </option>
				<option value="Autre"> Autre </option>
			</select>
			<br />
			<br />
			
			<input type="submit" name="ajouterEtudiant" value="Ajouter etudiant"/>
		</form>
		
		<br />
		<hr />
		<br />
		
		<form method="POST" action="etudiants" enctype="multipart/form-data">
	        <input type="file" name="fichier" id="fichier" />
	        
	        <br />
	        <br />
			<input type="submit" name="ajouterCsv" value="Ajouter liste etudiant">
		</form>
		
		<br />
		<hr />
		<br /> 
	</body>
</html>