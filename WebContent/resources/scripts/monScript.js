$("#BoutonClients").click(function() {
	$("#ListeClientsConseiller").slideToggle("medium");
	$("#ListeComptesbyClientId").slideToggle("medium");
});

$("#afficherclientsconseiller").click(function() {
	$("#ListeClients").slideToggle("medium");
	$("#ListeClientsConseiller").slideToggle("medium");
	$("#ListeComptesbyClientId").slideToggle("medium");
});

$("#affichertousclients").click(function() {
	$("#ListeClients").slideToggle("medium");
	$("#ListeClientsConseiller").slideToggle("medium");
	$("#ListeComptesbyClientId").slideToggle("medium");
});

$("#BoutonComptes").click(function() {
	$("#ListeComptesConseiller").slideToggle("medium");

});

$("#affichercomptesclients").click(function() {
	$("#ListeComptes").slideToggle("medium");
	$("#ListeComptesConseiller").slideToggle("medium");
});

$("#affichertouscomptes").click(function() {
	$("#ListeComptes").slideToggle("medium");
	$("#ListeComptesConseiller").slideToggle("medium");
});

$("#aide").click(function() {
	$("#tableau-aide").slideToggle("fast");
});

$("#afficherclientscomptes").click(function() {
	$("#ListeClientsConseiller").slideToggle("medium");
	$("#ListeClients").slideToggle("medium");
	$("#ListeComptesbyClientId").slideToggle("medium");
});