/*
 * Bytecode Analysis Framework
 * Copyright (C) 2003, University of Maryland
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umd.cs.daveho.ba;

import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.InstructionHandle;

/**
 * A ResourceTracker is used with ResourceValueAnalysis to determine
 * where in a method a certain kind of resource is created, and
 * to model the effect of instructions on the state of that resource.
 *
 * @see ResourceValueAnalysis
 * @author David Hovemeyer
 */
public interface ResourceTracker<Resource> {
	/**
	 * Determine if the given instruction is the site where a resource
	 * is created.
	 * @param basicBlock basic block containing the instruction
	 * @param handle the instruction
	 * @param cpg the ConstantPoolGen for the method
	 * @return an opaque Resource object if it is a creation site, or
	 *   null if it is not a creation site
	 */
	public Resource isResourceCreation(BasicBlock basicBlock, InstructionHandle handle, ConstantPoolGen cpg);

	/**
	 * Determine if the given instruction is the site where a resource
	 * is closed.
	 * @param basicBlock basic block containing the instruction
	 * @param handle the instruction
	 * @param cpg the ConstantPoolGen for the method
	 * @param resource the resource, as returned by isResourceCreation()
	 * @return true if the resource is closed here, false otherwise
	 */
	public boolean isResourceClose(BasicBlock basicBlock, InstructionHandle handle, ConstantPoolGen cpg, Resource resource);

	/**
	 * Create a ResourceValueFrameModelingVisitor to model the effect
	 * of instructions on the state of the resource.
	 * @param resource the resource we are tracking
	 * @param cpg the ConstantPoolGen of the method
	 * @return a ResourceValueFrameModelingVisitor
	 */
	public ResourceValueFrameModelingVisitor createVisitor(Resource resource, ConstantPoolGen cpg);
}

// vim:ts=4
