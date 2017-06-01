package main.java.models;


/**
 * @file
 * @author Benny Bobaganoosh <thebennybox@gmail.com>
 * @section LICENSE
 *
 * Copyright (c) 2014, Benny Bobaganoosh All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
public class Vertex
{
    private Vector4f m_pos;
    private Vector4f m_texCoords;
    private Vector4f m_normal;

    /**
     * Basic Getter
     */
    public float getX()
    {
        return m_pos.getX();
    }

    /**
     * Basic Getter
     */
    public float getY()
    {
        return m_pos.getY();
    }

    public Vector4f getPosition()
    {
        return m_pos;
    }

    public Vector4f GetTexCoords()
    {
        return m_texCoords;
    }

    public Vector4f getNormal()
    {
        return m_normal;
    }

    /**
     * Creates a new Vertex in a usable state.
     */
    public Vertex(Vector4f pos, Vector4f texCoords, Vector4f normal)
    {
        m_pos = pos;
        m_texCoords = texCoords;
        m_normal = normal;
    }

    public Vertex transform(Matrix4f transform, Matrix4f normalTransform)
    {
        // The normalized here is important if you're doing scaling.
        return new Vertex(transform.transform(m_pos), m_texCoords,
                normalTransform.transform(m_normal).normalized());
    }

    public Vertex perspectiveDivide()
    {
        return new Vertex(new Vector4f(m_pos.getX() / m_pos.getW(), m_pos.getY() / m_pos.getW(),
                m_pos.getZ() / m_pos.getW(), m_pos.getW()),
                m_texCoords, m_normal);
    }

    public float triangleAreaTimesTwo(Vertex b, Vertex c)
    {
        float x1 = b.getX() - m_pos.getX();
        float y1 = b.getY() - m_pos.getY();

        float x2 = c.getX() - m_pos.getX();
        float y2 = c.getY() - m_pos.getY();

        return (x1 * y2 - x2 * y1);
    }

    public Vertex lerp(Vertex other, float lerpAmt)
    {
        return new Vertex(
                m_pos.lerp(other.getPosition(), lerpAmt),
                m_texCoords.lerp(other.GetTexCoords(), lerpAmt),
                m_normal.lerp(other.getNormal(), lerpAmt)
        );
    }

    public boolean isInsideViewFrustum()
    {
        return Math.abs(m_pos.getX()) <= Math.abs(m_pos.getW())
                && Math.abs(m_pos.getY()) <= Math.abs(m_pos.getW())
                && Math.abs(m_pos.getZ()) <= Math.abs(m_pos.getW());
    }

    public float get(int index)
    {
        switch (index)
        {
        case 0:
            return m_pos.getX();
        case 1:
            return m_pos.getY();
        case 2:
            return m_pos.getZ();
        case 3:
            return m_pos.getW();
        default:
            throw new IndexOutOfBoundsException();
        }
    }
}
