package es.upm.aled.lab2.kinematics;

import es.upm.aled.lab2.gui.Node;

/**
 * This class implements a forward kinematics algorithm using recursion. It
 * expects a tree of Segments (defined by its length and angle with respect to
 * the previous Segment in the tree) and returns a tree of Nodes (defined by
 * their absolute coordinates in a 2-dimensional space).
 * 
 * @author rgarciacarmona
 */
public class ForwardKinematics {

	/**
	 * Returns a tree of Nodes to be used by SkeletonPanel to draw the position of
	 * an exoskeleton. This method is the public facade to a recursive method that
	 * builds the result from a tree of Segments defined by their angle and length,
	 * and the relationship between them (which Segment is children of which).
	 * 
	 * @param root    The root of the tree of Segments.
	 * @param originX The X coordinate for the origin point of the tree.
	 * @param originY The Y coordinate for the origin point of the tree.
	 * @return The tree of Nodes that represent the exoskeleton position in absolute
	 *         coordinates.
	 */
	// Public method: returns the root of the position tree
	public static Node computePositions(Segment root, double originX, double originY) {
		// Llamada al método privado recursivo pasando un accumulatedAngle inicial de 0.0
		return computePositions(root, originX, originY, 0.0);
	}

	// Private helper method that implements the recursive algorithm
	private static Node computePositions(Segment link, double baseX, double baseY, double accumulatedAngle) {
		// ------------------------------------------------------------------------
	    // 1. CÁLCULOS GEOMÉTRICOS PARA EL NODO ACTUAL
	    // ------------------------------------------------------------------------
		// Sumamos el ángulo del segmento actual al ángulo acumulado que viene de los padres
		double accumulatedAngleActual = accumulatedAngle + link.getAngle();
		
		// Calculamos las coordenadas del extremo final del segmento actual usando trigonometría
		double finalX = baseX + link.getLength() * Math.cos(accumulatedAngleActual);
		double finalY = baseY + link.getLength() * Math.sin(accumulatedAngleActual);
		
		// Creamos el nodo resultante que representa la posición calculada
		Node nodoActual = new Node(finalX, finalY);
		
		// ------------------------------------------------------------------------
	    // 2. CASO BASE: EL SEGMENTO NO TIENE HIJOS (ES UNA HOJA DEL ÁRBOL)
	    // ------------------------------------------------------------------------
		if(link.getChildren().isEmpty()) {
			//Devolvemos el nodo actual sin hijos
			return nodoActual;
		}
		
		// ------------------------------------------------------------------------
	    // 3. PASO RECURSIVO: RECORRIDO DE LOS HIJOS
	    // ------------------------------------------------------------------------
		for(Segment childSegment: link.getChildren()) {
			// Llamada recursiva pasando:
	        // - childSegment: el segmento hijo actual
	        // - endX, endY: las coordenadas recién calculadas que sirven de base para el hijo
	        // - currentAccumulatedAngle: el nuevo ángulo acumulado actualizado
			Node childNode = computePositions(childSegment, finalX, finalY, accumulatedAngleActual);
			
			// Añadimos el Node resultante a la lista de hijos del Node actual
			nodoActual.addChild(childNode);
		}
		
		// Retornamos el nodo actual junto con toda su jerarquía de hijos procesada
		return nodoActual;
	}
}