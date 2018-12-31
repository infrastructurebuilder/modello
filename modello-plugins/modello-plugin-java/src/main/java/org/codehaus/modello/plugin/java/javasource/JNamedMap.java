/**
 * Redistribution and use of this software and associated documentation
 * ("Software"), with or without modification, are permitted provided
 * that the following conditions are met:
 *
 * 1. Redistributions of source code must retain copyright
 *    statements and notices.  Redistributions must also contain a
 *    copy of this document.
 *
 * 2. Redistributions in binary form must reproduce the
 *    above copyright notice, this list of conditions and the
 *    following disclaimer in the documentation and/or other
 *    materials provided with the distribution.
 *
 * 3. The name "Exolab" must not be used to endorse or promote
 *    products derived from this Software without prior written
 *    permission of Intalio, Inc.  For written permission,
 *    please contact info@codehaus.org.
 *
 * 4. Products derived from this Software may not be called "Exolab"
 *    nor may "Exolab" appear in their names without prior written
 *    permission of Intalio, Inc. Exolab is a registered
 *    trademark of Intalio, Inc.
 *
 * 5. Due credit should be given to the Exolab Project
 *    (http://www.codehaus.org/).
 *
 * THIS SOFTWARE IS PROVIDED BY INTALIO, INC. AND CONTRIBUTORS
 * ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 * INTALIO, INC. OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * Copyright 1999 (C) Intalio, Inc. All Rights Reserved.
 *
 * $Id$
 */
package org.codehaus.modello.plugin.java.javasource;

/*
 * Copyright (c) 2004, Codehaus.org
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * A simple String to Object mapping which preserves order.
 * </p>
 * <strong>Note:</strong>
 * This class is not synchronized. So be careful. :-)
 *
 * @author <a href="mailto:kvisco@intalio.com">Keith Visco</a>
 * @deprecated use {@link LinkedHashMap} instead
 **/
@Deprecated
public class JNamedMap
{

    private List<String> names = null;
    private List<Object> objects = null;

    /**
     * Creates a new JNamedMap
     **/
    public JNamedMap()
    {

        names = new ArrayList<String>();
        objects = new ArrayList<Object>();

    } //-- JNamedMap

    /**
     * Creates a new JNamedMap with the given size.
     *
     * @param size the initial size for this NamedMap
     **/
    public JNamedMap( int size )
    {

        names = new ArrayList<String>( size );
        objects = new ArrayList<Object>( size );

    } //-- JNamedMap


    /**
     * Returns the Object associated with the given name.
     *
     * @param name the name to search for
     * @return the Object associated with the given name
     **/
    public Object get( String name )
    {
        int i = indexOf( name );
        if ( i >= 0 ) return objects.get( i );
        return null;
    } //-- get

    /**
     * Returns the Object at the given index.
     *
     * @param index the index of the Object to return
     * @return the Object at the given index
     **/
    public Object get( int index )
        throws IndexOutOfBoundsException
    {
        return objects.get( index );
    } //-- get

    /**
     * Returns the name associated with the given Object
     *
     * @param obj the Object to search for
     * @return the name of the given Object
     **/
    public String getNameByObject( Object obj )
    {
        int i = objects.indexOf( obj );
        if ( i >= 0 ) return (String) names.get( i );
        return null;
    } //-- getNameByObject

    /**
     * Return a Vector of names
     *
     * @return a Vector of names
     **/
    public java.util.Vector<String> getNames()
    {
        return new java.util.Vector<String>( names );
    } //-- getNames

    /**
     * Return a Vector of Objects
     *
     * @return a Vector of Objects
     **/
    public java.util.Vector<Object> getObjects()
    {
        return new java.util.Vector<Object>( objects );
    } //-- getObjects

    /**
     * Returns the index of the Object which has been
     * mapped (associated) with the given name
     *
     * @param name the name
     * @return the index of the Object which has been mapped (associated)
     * to the given name
     **/
    public int indexOf( String name )
    {

        for ( int i = 0; i < names.size(); i++ )
        {
            String iName = (String) names.get( i );
            if ( iName.equals( name ) ) return i;
        }
        return -1;

    } //-- indexOf

    /**
     * Maps (associates) an Object with a name
     *
     * @param name the name to associate with the given Object
     * @param obj the Object to be mapped
     **/
    public void put( String name, Object obj )
    {

        int idx = indexOf( name );

        if ( idx >= 0 )
            objects.add( idx, obj );
        else
        {
            //-- we may need some synchronization here
            //-- if we are in a multithreaded environment
            names.add( name );
            objects.add( obj );
        }
    } //-- put

    /**
     * Removes and returns the Object located at the given index
     *
     * @param index the index of the Object to remove
     * @return the object removed from the map.
     **/
    public Object remove( int index )
        throws IndexOutOfBoundsException
    {
        Object obj = objects.get( index );
        objects.remove( index );
        names.remove( index );
        return obj;
    } //-- remove

    /**
     * Removes and returns the Object associated with the given name
     *
     * @param name the name of the Object to remove
     * @return the object removed from the map.
     **/
    public Object remove( String name )
    {

        Object obj = null;

        int idx = indexOf( name );
        if ( idx >= 0 )
        {
            obj = objects.get( idx );
            objects.remove( idx );
            names.remove( idx );
        }
        return obj;
    } //-- remove

    /**
     * Returns the number of Object associations currently in
     * this named map
     *
     * @return the number of Object associations currently in
     * this named map
     **/
    public int size()
    {
        return names.size();
    } //-- size

} //-- JNamedMap