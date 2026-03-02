/*
 * Copyright (c) 2002-2026, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.announce.service;

import javax.cache.CacheException;

import fr.paris.lutece.plugins.announce.business.Announce;
import fr.paris.lutece.plugins.announce.business.Category;
import fr.paris.lutece.portal.service.cache.AbstractCacheableService;
import fr.paris.lutece.portal.service.util.AppLogService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Cache service for announces
 */
@ApplicationScoped
public class AnnounceCacheService extends AbstractCacheableService<String, Object>
{
    private static final String CACHE_SERVICE_NAME = "announce.announceCacheService";
    private static final String ANNOUNCE_KEY_PREFIXE = "announce.announce.";
    private static final String CATEGORY_KEY_PREFIXE = "announce.category.";
    private static final String PUBLISHED_ANNOUNCES_ID_LIST_KEY_PREFIXE = "announce.announce.allPublishedId";

    @PostConstruct
    public void init( )
    {
        initCache( CACHE_SERVICE_NAME, String.class, Object.class );
    }

    /**
     * Get the cache key of an announce
     *
     * @param nIdAnnounce
     *            The id of the announce to get the key of
     * @return The cache key of the announce
     */
    public static String getAnnounceCacheKey( int nIdAnnounce )
    {
        return ANNOUNCE_KEY_PREFIXE + nIdAnnounce;
    }

    /**
     * Get the cache key of a category
     *
     * @param nIdCategory
     *            The id of the category to get the key of
     * @return The cache key of the category
     */
    public static String getCategoryCacheKey( int nIdCategory )
    {
        return CATEGORY_KEY_PREFIXE + nIdCategory;
    }

    /**
     * Get the cache key of the list of published announces for a given sort
     *
     * @param strSortColumn
     *            The sort column name
     * @param bSortAsc
     *            True if sort is ascending
     * @return The cache key of the list of published announces
     */
    public static String getListIdPublishedAnnouncesCacheKey( String strSortColumn, boolean bSortAsc )
    {
        return PUBLISHED_ANNOUNCES_ID_LIST_KEY_PREFIXE + "." + strSortColumn + "." + bSortAsc;
    }

    /**
     * Get the cache key prefix for published announces lists
     *
     * @return The cache key prefix
     */
    public static String getListIdPublishedAnnouncesCacheKeyPrefix( )
    {
        return PUBLISHED_ANNOUNCES_ID_LIST_KEY_PREFIXE;
    }

    /**
     * Get the cache key prefix for categories
     *
     * @return The cache key prefix
     */
    public static String getCategoryCacheKeyPrefix( )
    {
        return CATEGORY_KEY_PREFIXE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName( )
    {
        return CACHE_SERVICE_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get( String strKey )
    {
        if ( isCacheEnable( ) && isCacheAvailable( ) )
        {
            try
            {
                Object object = super.get( strKey );

                if ( object != null )
                {
                    if ( object instanceof Announce )
                    {
                        return ( (Announce) object ).clone( );
                    }

                    if ( object instanceof Category )
                    {
                        return ( (Category) object ).clone( );
                    }
                }

                return object;
            }
            catch( CacheException | IllegalStateException e )
            {
                AppLogService.error( "Cache get error for key {}", strKey, e );
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put( String strKey, Object object )
    {
        if ( isCacheEnable( ) && isCacheAvailable( ) )
        {
            try
            {
                Object clonedObject = object;

                if ( object instanceof Announce )
                {
                    clonedObject = ( (Announce) object ).clone( );
                }

                if ( object instanceof Category )
                {
                    clonedObject = ( (Category) object ).clone( );
                }

                super.put( strKey, clonedObject );
            }
            catch( CacheException | IllegalStateException e )
            {
                AppLogService.error( "Cache put error for key {}", strKey, e );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove( String strKey )
    {
        if ( isCacheEnable( ) && isCacheAvailable( ) )
        {
            try
            {
                return super.remove( strKey );
            }
            catch( CacheException | IllegalStateException e )
            {
                AppLogService.error( "Cache remove error for key {}", strKey, e );
            }
        }

        return false;
    }

    private boolean isCacheAvailable( )
    {
        return _cache != null && !_cache.isClosed( );
    }
}
